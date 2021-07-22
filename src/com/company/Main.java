package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String connectionUrl = "jdbc:postgresql://localhost:5432/Cinema";
		String username= "postgres";
		String password= "qwerty2002";
		try {
			Connection con= DriverManager.getConnection(connectionUrl,username,password);
			System.out.println("Connected to Postgresql server");
			ResultSet rs;
			Statement statement = con.createStatement();
			Scanner num = new Scanner(System.in);
			String word;
			int san;
			while(true){
				System.out.println("Hello Admin!");
				System.out.println("1. See all customers \n2. Add customer account \n3. See all movies \n4. Add movie\n5. Delete movie \n6. See subscription list \n7. To quit" );
				san= num.nextInt();
				if (san==7){
					break;
				}

				switch (san){
					case 1:
						String cust=("select * from customers_account");
						rs =statement.executeQuery(cust);
						while (rs.next()){
							int customer_id = rs.getInt("customer_id");
							String firstname=rs.getString("firstname");
							String lastname=rs.getString("lastname");
							String email=rs.getString("email");
							int phone = rs.getInt("phone_number");
							String login=rs.getString("login");
							String passwrd=rs.getString("password");
							int sub_id=rs.getInt("subscription_id");
							System.out.println("Customer ID | First Name | Last Name | Email | Phone number | Login | Password | Subscription type");
							System.out.printf("%d | %s | %s | %s | %s | %s | %s | %s\n",customer_id,firstname,lastname,email,phone,login,passwrd,sub_id);
						}

						continue;
					case 2:
						String cus=("select * from customers_account");
						rs =statement.executeQuery(cus);
						System.out.println("Adding new entry\ninput Customer's name:");
						word=num.next();
						System.out.println("input Customer's lastname:");
						String name;
						name= num.next();
						System.out.println("input Customer's email:");
						String email;
						email= num.next();
						System.out.println("input Customer's phone:");
						String phn;
								phn= num.next();
						System.out.println("input Customer's login:");
						String login;
								login= num.next();
						System.out.println("input Customer's password:");
						String pswrd;
								pswrd= num.next();
						System.out.println("input Customer's subscription:");
						int subs;
								subs= num.nextInt();
						int i = rs.getInt("customer_id");
						i= i++;
						String sql = "INSERT INTO customers_account(customer_id, firstname, lastname, email, phone_number, login, password, subscription_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement statement1 = con.prepareStatement(sql);
						int rows = statement1.executeUpdate();
						if (rows>i) {
							System.out.println("A customer was added");
						}
						continue;
					case 3:
						String slct=("select * from movies");
						rs =statement.executeQuery(slct);
						while (rs.next()){
							int id = rs.getInt("movie_id");
							String movie_name=rs.getString("movie_title");
							String release_date=rs.getString("release_date");
							String rating=rs.getString("rating");
							int sub_id=rs.getInt("subscription_id");
							System.out.println("Movie ID | Movie Title | Release Date | Rating | Subscription ID");
							System.out.printf("%d | %s | %s | %s | %s\n",id,movie_name,release_date,rating,sub_id);
						}
						continue;
					case 4:


						continue;
					case 6:
						String sub=("select * from subsсription");
						rs =statement.executeQuery(sub);
						while (rs.next()){
							int sub_id = rs.getInt("subscription_id");
							int price = rs.getInt("price");
							String subscription_type=rs.getString("subscription_type");
							String validity=rs.getString("validity");
							System.out.println("Subscription | Type | Validity ");
							System.out.printf("%d | %s | %s | %s \n",sub_id,price,subscription_type,validity);
						}
						continue;

				}
			}

		} catch (SQLException e) {
			System.out.println("Error in connecting to Postgresql server");
			e.printStackTrace();
		}
	}
}