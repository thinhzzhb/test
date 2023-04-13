package com.example.test.servlet;

import com.example.test.dao.NhanvienDAO;
import com.example.test.model.NhanVienEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@WebServlet({"/nhanvien/index", "/nhanvien/add", "/nhanvien/detail/*", "/nhanvien/delete"})
public class NhanvienServlet extends HttpServlet {
    NhanvienDAO dao = new NhanvienDAO();
    private NhanVienEntity entity;
    NhanVienEntity nvEntity = new NhanVienEntity();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        List<NhanVienEntity> listE = dao.getAll();
        if (uri.contains("detail")) {
            String idStr = uri.substring(uri.lastIndexOf("/") + 1);
            UUID id = UUID.fromString(idStr);
            nvEntity = dao.findById(id);
        }
        request.setAttribute("entity", nvEntity);
        request.setAttribute("list", listE);
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }

    private void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NhanVienEntity> listE = dao.getAll();
        request.setAttribute("list", listE);
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (uri.contains("add")) {
                addEmployee(response, request);
            }else if (uri.contains("delete")) {
                delete(request);
            }
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addEmployee(HttpServletResponse response, HttpServletRequest request) {
        NhanVienEntity entity = new NhanVienEntity();
        String fullname = request.getParameter("fullname");
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            String[] parts = fullname.split("\\s+");
             if (parts.length > 2) {
                // Nếu có đầy đủ họ tên đệm và tên
                StringBuilder tenDem = new StringBuilder();
                for (int i = 1; i < parts.length - 1; i++) {
                    tenDem.append(parts[i]).append(" ");
                }
                entity.setHo(parts[0]);
                entity.setTenDem(tenDem.toString().trim());
                entity.setTen(parts[parts.length - 1]);
            }
            entity.setMa(dao.getMa(entity.getFullname()));
            dao.add(entity);
            request.setAttribute("message", "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Thêm thất bại do lỗi: " + e.toString());
        }
    }
    private void delete(HttpServletRequest request){
        try {
            dao.delete(nvEntity);
            request.setAttribute("message", "xóa thành công" );
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Xóa thất bại do lỗi: " + e.toString());
        }
    }
}
