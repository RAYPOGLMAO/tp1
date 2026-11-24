package isep.lab1.util;

import java.io.PrintWriter;

/** Tiny helpers so the servlets stay short. */
public final class Html {
    private Html() { }

    /** Escapes HTML special characters (avoids XSS when echoing user input). */
    public static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    public static void head(PrintWriter out, String title) {
        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>" + esc(title) + "</title></head><body>");
        out.println("<h1>" + esc(title) + "</h1>");
    }

    public static void foot(PrintWriter out) {
        out.println("<p><a href=\"index.html\">Home</a></p></body></html>");
    }
}
