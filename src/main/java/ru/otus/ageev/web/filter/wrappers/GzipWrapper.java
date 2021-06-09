package ru.otus.ageev.web.filter.wrappers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipWrapper extends HttpServletResponseWrapper {
    private PrintWriter printWriter;
    private GzipOutputStream gzipOutputStream;

    public GzipWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void flushBuffer() throws IOException {
        if (Objects.nonNull(printWriter)) {
            printWriter.flush();
        }
        if (Objects.nonNull(gzipOutputStream)) {
            gzipOutputStream.flush();
        }
        super.flushBuffer();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (Objects.nonNull(printWriter)) {
            throw new IllegalStateException("PrintWriter already obtained");
        }
        if (Objects.isNull(gzipOutputStream)) {
            gzipOutputStream = new GzipOutputStream(getResponse().getOutputStream());
        }
        return gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (Objects.isNull(printWriter) && Objects.nonNull(gzipOutputStream)) {
            throw new IllegalStateException("OutputStream already obtained");
        }
        if (Objects.isNull(printWriter)) {
            gzipOutputStream = new GzipOutputStream(getResponse().getOutputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return printWriter;
    }

    public void close() throws IOException {
        if (Objects.nonNull(printWriter)) {
            printWriter.close();
        }
        if (Objects.nonNull(gzipOutputStream)) {
            gzipOutputStream.close();
        }
    }
}
