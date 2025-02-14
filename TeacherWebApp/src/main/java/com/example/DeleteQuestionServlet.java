package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteQuestion")
public class DeleteQuestionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter("id"));
        int moduleId = Integer.parseInt(request.getParameter("moduleId"));

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM AnswerValue WHERE idAnswerValue = ?")) {
            statement.setInt(1, questionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("ModuleDetails.jsp?id=" + moduleId);
    }
}

