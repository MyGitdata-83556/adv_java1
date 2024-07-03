


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

package com.sunbeam;

public class CandidateDao {

 
    private Connection connection;

    public CandidateDaoImpl() throws Exception {
        // Initialize the database connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/election_db", "root", "password");
    }

    public void close() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }

    public List<Candidate> findAll() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            candidates.add(new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("party"), rs.getInt("votes")));
        }
        return candidates;
    }

    public List<Candidate> findByParty(String party) throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates WHERE party = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, party);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            candidates.add(new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("party"), rs.getInt("votes")));
        }
        return candidates;
    }

    public List<Candidate> findAllOrderByVotesDesc() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates ORDER BY votes DESC";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            candidates.add(new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("party"), rs.getInt("votes")));
        }
        return candidates;
    }

    public int save(Candidate candidate) throws SQLException {
        String query = "INSERT INTO candidates (name, party, votes) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, candidate.getName());
        stmt.setString(2, candidate.getParty());
        stmt.setInt(3, candidate.getVotes());
        int rows = stmt.executeUpdate();
        if (rows > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int deleteById(int id) throws SQLException {
        String query = "DELETE FROM candidates WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }

    public int update(Candidate candidate) throws SQLException {
        String query = "UPDATE candidates SET name = ?, party = ?, votes = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, candidate.getName());
        stmt.setString(2, candidate.getParty());
        stmt.setInt(3, candidate.getVotes());
        stmt.setInt(4, candidate.getId());
        return stmt.executeUpdate();
    }

    public Candidate findById(int id) throws SQLException {
        String query = "SELECT * FROM candidates WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Candidate(rs.getInt("id"), rs.getString("name"), rs.getString("party"), rs.getInt("votes"));
        }
        return null;
    }

    public int incrementVotes(int id) throws SQLException {
        String query = "UPDATE candidates SET votes = votes + 1 WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }
}
