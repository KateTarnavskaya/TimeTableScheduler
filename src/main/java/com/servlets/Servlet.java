package com.servlets;

import com.scheduler.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;


public class Servlet extends HttpServlet implements ServletContext {
    List<String> studentgroup, nosubject, stgrpsubject, subjecttime;
    List<String> teacher, teachersubject;
    String hoursperday, breakstart, breakend, daysperweek;
    int cumusubcount = 0;

    public Servlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //invokes method to take input
        new InputData().takeinput();
        //invokes algorithm
        new SchedulerMain();
        //grabs final chromosome i.e. the output
        Chromosome finalson =SchedulerMain.finalson;
        req.setAttribute("son", finalson);
        resp.getWriter().println(finalson.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }



    public String fromForm() {

        InputData id = new  InputData();

        InputData.daysperweek = Integer.parseInt(daysperweek);
        InputData.hoursperday = Integer.parseInt(hoursperday);
        InputData.nostudentgroup = studentgroup.size();

        for (int i = 0; i < studentgroup.size(); i++) {

            InputData.studentgroup[i] = new StudentGroup();
            StudentGroup temp =  InputData.studentgroup[i];

            temp.setId(i);
            temp.setName(studentgroup.get(i));
            temp.setNosubject(nosubject.get(i));

            int nosub = Integer.parseInt(nosubject.get(i));
            String[] sub = new String[nosub];
            int[] hrs = new int[nosub];
            for (int j = 0; j < nosub; j++) {
                sub[j] = stgrpsubject.get(cumusubcount);
                hrs[j] = Integer.parseInt(subjecttime.get(cumusubcount));
                cumusubcount++;
            }

            temp.setSubject(sub);
            temp.setHours(hrs);

        }

        InputData.noteacher = teacher.size();
        for (int i = 0; i < teacher.size(); i++) {

            InputData.teacher[i] = new Teacher();
            Teacher tmp =  InputData.teacher[i];

            tmp.setId(i);
            tmp.setName(teacher.get(i));
            tmp.setSubject(teachersubject.get(i));
        }

        //after getting all input, teachers are assigned to each subject
        id.assignTeacher();

        new SchedulerMain();

        Chromosome finalson = SchedulerMain.finalson;
//        getServletRequest().setAttribute("son", finalson);
//        return SUCCESS;
    }

    @Override
    public String getContextPath() {
        return null;
    }

    @Override
    public ServletContext getContext(String uripath) {
        return null;
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public String getMimeType(String file) {
        return null;
    }

    @Override
    public Set getResourcePaths(String path) {
        return null;
    }

    @Override
    public URL getResource(String path) throws MalformedURLException {
        return null;
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        return null;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return null;
    }

    @Override
    public javax.servlet.Servlet getServlet(String name) throws ServletException {
        return null;
    }

    @Override
    public Enumeration getServlets() {
        return null;
    }

    @Override
    public Enumeration getServletNames() {
        return null;
    }

    @Override
    public void log(Exception exception, String msg) {

    }

    @Override
    public String getRealPath(String path) {
        return null;
    }

    @Override
    public String getServerInfo() {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    @Override
    public Enumeration getAttributeNames() {
        return null;
    }

    @Override
    public void setAttribute(String name, Object object) {

    }

    @Override
    public void removeAttribute(String name) {

    }

    @Override
    public String getServletContextName() {
        return null;
    }
}
