import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberHelper {
	/**
	 * Register New Member to our database ( insert ) by given Member Object and password
	 * @param member [Member]
	 * @param password [String]
	 * @return Member Object On Success | null to failed
	 */
	public Member register( Member member , String password) {
		try{
			if( ! isExist( member.getEmail() ) ){
				DataBaseHelper db  = new DataBaseHelper();
				String[] keys   = { "name" , "last_name" , "address" , "phone" , "email" , "password" };
				String[] values = {
						member.getFirstName(),
						member.getLastName(),
						member.getAddress(),
						member.getPhone(),
						member.getEmail().getEmailString(),
						PasswordUtils.generateSecurePassword( password , PasswordUtils.getSalt() )
				};
				if( db.insert( "members" ,keys , values ) ){
					Member newMember = this.GetByEmail( member.getEmail() );
					return newMember;
				}
			} else{
				return null;
			}
		} catch ( Exception e ){}
		return null;
	};

	public Member login( Email email , String password ){
		try{
			Member tmpMember = GetByEmail( email );
			if( tmpMember != null ){
				if( PasswordUtils.verifyUserPassword( password , tmpMember.getSecuredPassword() ,  PasswordUtils.getSalt() ) ){
					return tmpMember;
				} else{
				}
			}
		} catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return new Member();
	}

	/**
	 * Check if member exist by ID(int)
	 * @param ID
	 * @return true on exist | false on failed
	 */
	public boolean isExist( int ID ) {
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getResult( "SELECT ID FROM members WHERE ID =" + ID );
			if( rs.next() ) {
				return true;
			}
			rs.close();

		}catch ( Exception e ){}
		return false;
	};
	/**
	 * Check if member exist by email(Email)
	 * @param email
	 * @return true on exist | false on failed
	 */
	public boolean isExist( Email email ) {
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getResult( "SELECT ID FROM members WHERE email ='" + email.getEmailString() + "'" );
			if( rs.next() ) {
				return true;
			}
			rs.close();

		}catch ( Exception e ){ System.out.println( e.getMessage() );}
		return false;
	};

	/**
	 * Return Member object by given exist email address ( email is unique )
	 * @param email
	 * @return Member
	 */
	public Member GetByEmail( Email email ) {
		DataBaseHelper db  = new DataBaseHelper();
		try{
			ResultSet rs   = db.getResult( "SELECT * FROM members WHERE email ='" + email.getEmailString() + "'" );
			if( rs.next() ){
				return new Member(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("last_name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("email")
				);
			}
		}catch ( Exception e ){
			System.out.println( e.getMessage());
		}
		return null;
	};
	/**
	 * Return Member object by given exist ID ( ID is unique )
	 * @param ID
	 * @return member
	 */
	public Member GetByID( int ID ) {
		DataBaseHelper db  = new DataBaseHelper();
		try{
			ResultSet rs   = db.getResult( "SELECT * FROM members WHERE ID =" + ID );
			if( rs.next() ){
				return new Member(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("last_name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("email")
				);
			}
		}catch ( Exception e ){}
		return null;
	};

	/**
	 * Update Member Fields in the DataBase
	 * @param member [Member]
	 * @throws SQLException
	 */
	public void update(Member member) throws SQLException {
		DataBaseHelper db  = new DataBaseHelper();
		db.update( "members" , "name" , member.getFirstName() , member.getId() );
		db.update( "members" , "last_name" , member.getLastName() , member.getId() );
		db.update( "members" , "address" , member.getAddress() , member.getId() );
		db.update( "members" , "phone" , member.getPhone() , member.getId() );
	};
	public void ImportWordFile(){

	};

	/**
	 * Get ArrayList of all the Members in the database
	 * @return ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Member> getAllMembers() throws SQLException {
		ArrayList<Member> allMembers= new ArrayList<Member>();
		try{
			DataBaseHelper db  = new DataBaseHelper();
			ResultSet rs       = db.getTableResultSet( "members" );
			while( rs.next() ) {
				allMembers.add( new Member( rs.getInt("id") , rs.getString("name") , rs.getString("last_name") , rs.getString("address"), rs.getString("phone"), rs.getString("email") ) );
			}
			rs.close();
		}catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		return allMembers;
	}

	/**
	 * Print cards of all Members that registered in the DataBase.
	 * @throws SQLException
	 */
	public void printAllMembers()throws SQLException {
		ArrayList<Member> allMembers = new ArrayList<Member>();
		allMembers = this.getAllMembers();
		for( int i=0;i<allMembers.size(); i++){
			Member tmpMember = allMembers.get(i);
			System.out.println( "Name: " + tmpMember.getFirstName() + " " + tmpMember.getLastName() + " (" + tmpMember.getId() + ")");
			System.out.println( "Email: " + tmpMember.getEmail().getEmailString() );
			System.out.println( "Phone: " + tmpMember.getPhone() );
			System.out.println( "Address: " + tmpMember.getAddress() );
			System.out.println( "-------");
		}
	}
}
