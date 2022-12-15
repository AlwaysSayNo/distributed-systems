package org.nazar.grynko.web_crud;

import org.nazar.grynko.cases.Cases;
import org.nazar.grynko.dao.DBConsts;
import org.nazar.grynko.dao.GroupDao;
import org.nazar.grynko.dao.SingleConnectionPool;
import org.nazar.grynko.dao.StudentDao;
import org.nazar.grynko.processor.RequestProcessor;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "crudServlet", value = "/api")
public class CrudServlet extends HttpServlet {

    private Cases cases;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("op");
        String result;

        switch (operation) {
            case "1": {
                result = cases.case1();
                break;
            }
            case "2": {
                result = cases.case2();
                break;
            }
            case "3": {
                result = cases.case3();
                break;
            }
            case "4": {
                result = cases.case4();
                break;
            }
            case "5": {
                result = cases.case5();
                break;
            }
            case "6": {
                result = cases.case6();
                break;
            }
            case "7": {
                result = cases.case7();
                break;
            }
            default: {
                result = null;
                break;
            }
        }

        request.setAttribute("op", operation);
        request.setAttribute("result", result);

        getServletContext().getRequestDispatcher("/api.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();

        DBConsts dbConsts = new DBConsts();
        SingleConnectionPool singleConnectionPool = new SingleConnectionPool(dbConsts);

        StudentDao studentDao = new StudentDao(singleConnectionPool);
        GroupDao groupDao = new GroupDao(studentDao, singleConnectionPool);

        RequestProcessor requestProcessor = new RequestProcessor(groupDao, studentDao);

        this.cases = new Cases(requestProcessor);
    }
}