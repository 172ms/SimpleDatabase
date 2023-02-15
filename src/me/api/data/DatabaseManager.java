package me.api.data;

import java.sql.*;

public class DatabaseManager {
	private static DatabaseManager databaseManager = new DatabaseManager();
	private Connection connection;
	
	public static DatabaseManager getDatabaseManager() {
		return DatabaseManager.databaseManager;
	}
	
	public Connection connect() {
		if (this.connection != null) {
			return this.connection;
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/fluxmber?useSSL=false", "root", "");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void setup() {
		try {
			Statement statement = this.connect().createStatement();
			String create = "CREATE TABLE IF NOT EXISTS playerAPI (UUID varchar(100) primary key, RUB int, blocks int, kills int, deaths int, lastJoin varchar(100), firstJoin varchar(100))";
			statement.execute(create);
			statement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(PlayerAPI playerAPI) {
		try {
			PreparedStatement preparedStatement = this.connect().prepareStatement("INSERT INTO playerAPI(UUID, RUB, blocks, kills, deaths, lastJoin, firstJoin) VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, playerAPI.getUUID());
			preparedStatement.setInt(2, playerAPI.getRUB());
			preparedStatement.setInt(3, playerAPI.getBlocks());
			preparedStatement.setInt(4, playerAPI.getKills());
			preparedStatement.setInt(5, playerAPI.getDeaths());
			preparedStatement.setString(6, playerAPI.getLastJoin());
			preparedStatement.setString(7, (playerAPI.getFirstJoin()));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PlayerAPI load(String UUID) {
		try {
			PreparedStatement preparedStatement = this.connect().prepareStatement("SELECT * FROM playerAPI WHERE UUID = ?");
			preparedStatement.setString(1, UUID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				PlayerAPI playerAPI = new PlayerAPI(resultSet.getString("UUID"), resultSet.getInt("RUB"), resultSet.getInt("blocks"), resultSet.getInt("kills"), resultSet.getInt("deaths"), resultSet.getString("lastJoin"), resultSet.getString("firstJoin"));
				preparedStatement.close();
				return playerAPI;
			}
			preparedStatement.close();
			return null;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void save(PlayerAPI playerAPI) {
		try {
			PreparedStatement preparedStatement = this.connect().prepareStatement("UPDATE playerAPI SET RUB = ?, blocks = ?, kills = ?, deaths = ?, lastJoin = ?, firstJoin = ? WHERE UUID = ?");
			preparedStatement.setInt(1, playerAPI.getRUB());
			preparedStatement.setInt(2, playerAPI.getBlocks());
			preparedStatement.setInt(3, playerAPI.getKills());
			preparedStatement.setInt(4, playerAPI.getDeaths());
			preparedStatement.setString(5, playerAPI.getLastJoin());
			preparedStatement.setString(6, playerAPI.getFirstJoin());
			preparedStatement.setString(7, playerAPI.getUUID());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}