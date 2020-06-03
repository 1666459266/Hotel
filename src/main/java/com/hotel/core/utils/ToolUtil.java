package com.hotel.core.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class ToolUtil {

    public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    public static DecimalFormat df1 = new DecimalFormat("0.00");// 保留两位小数
    public static DecimalFormat df2 = new DecimalFormat("0,000.00");// 保留两位小数，逗号分割

    public static String maxLen(String s, int max) {
        if (StringUtils.isEmpty(s) || max <= 0)
            return "";
        int len = s.length();
        return len < max ? s : s.substring(0, max);
    }

    public static String str(Object obj) {
        if (obj == null || "null".equals(obj.toString().toLowerCase()))
            return "";
        return StringUtils.isEmpty(obj + "") ? "" : (obj + "").trim();
    }

    public static String str(String key, Map<String, Object> m) {
        if (m == null)
            return "";
        Object obj = m.get(key);
        return obj == null ? "" : obj.toString().trim();
    }

    public static String str(String key, HttpServletRequest r) {
        String s = r.getParameter(key);
        return s == null ? "" : s.trim();
    }

    public static int integer(String val) {
        int r = -1;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Integer.parseInt(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static int integer(String key, Map<String, Object> m) {
        String val = str(key, m);
        int r = -1;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Integer.parseInt(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static int integer(String key, HttpServletRequest req) {
        String val = str(key, req);
        int r = -1;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Integer.parseInt(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static Integer intWithNull(String key, HttpServletRequest req) {
        String val = str(key, req);
        Integer r = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Integer.parseInt(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static long lon(String val) {
        long r = -1;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Long.parseLong(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static Long lonWithNull(String val) {
        Long r = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Long.parseLong(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static Long lonWithNull(String key, HttpServletRequest req) {
        String val = str(key, req);
        Long r = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Long.parseLong(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static long lon(String key, Map<String, Object> m) {
        String val = str(key, m);
        long r = -1L;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Long.parseLong(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static long lon(String key, HttpServletRequest req) {
        String val = str(key, req);
        long r = -1;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Long.parseLong(val, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static double dbl(String key, Map<String, Object> m) {
        String val = str(key, m);
        double r = -1.0;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Double.parseDouble(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static double dbl1(String key, Map<String, Object> m) {
        String val = str(key, m);
        double r = 0;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Double.parseDouble(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static double dbl(String key, HttpServletRequest req) {
        String val = str(key, req);
        double r = -1.0;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = Double.parseDouble(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static BigDecimal dec(String key, Map<String, Object> m) {
        return dec(key, m, false);
    }

    public static BigDecimal dec(String key, Map<String, Object> m, boolean useNull) {
        String val = str(key, m);
        if (useNull && StringUtils.isEmpty(val))
            return null;

        BigDecimal r = new BigDecimal(0);
        if (!StringUtils.isEmpty(val)) {
            try {
                r = new BigDecimal(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static BigDecimal dec(String val) {
        BigDecimal r = new BigDecimal(0);
        if (!StringUtils.isEmpty(val)) {
            try {
                r = new BigDecimal(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static BigDecimal decWithNull(String key, Map<String, Object> m) {
        String val = str(key, m);
        if (StringUtils.isEmpty(val))
            return null;
        BigDecimal r = null;
        try {
            r = new BigDecimal(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static BigDecimal dec(String key, HttpServletRequest req) {
        String val = str(key, req);
        BigDecimal r = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                r = new BigDecimal(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public static Date date(String key, Map<String, Object> m) {
        Date date = null;
        Object obj = m.get(key);
        if (obj != null) {
            try {
                date = (Date) obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static Date date2(String key, HttpServletRequest req) {
        String val = str(key, req);
        Date date = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                date = sdf2.parse(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static Date date1(String key, HttpServletRequest req) {
        String val = str(key, req);
        Date date = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                date = sdf1.parse(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static Date date1(String key) {
        String val = str(key);
        Date date = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                date = sdf1.parse(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    // date字符串：2017-01-01
    public static Date date4(String key, HttpServletRequest req) {
        String val = str(key, req);
        Date date = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                date = sdf1.parse(val + " 00:00:00");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static Date date2(String key, Map<String, Object> map) {
        String val = str(key, map);
        Date date = null;
        if (!StringUtils.isEmpty(val)) {
            try {
                date = sdf2.parse(val);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String decode(String key, HttpServletRequest request) {
        String val = str(key, request);
        if (!StringUtils.isEmpty(val)) {
            try {
                val = URLDecoder.decode(val, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    public static Map<String, Object> getResult() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        return map;
    }

    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        return basePath;
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // 删除HTML标签
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }

    public static String getProductPkcorp(String pncnum) {
        if (StringUtils.isEmpty(pncnum))
            return null;
        String[] ss = pncnum.split("_");
        if (ss.length > 1)
            return ss[1];
        return null;
    }

    public static String getProductInvcode(String pncnum) {
        if (StringUtils.isEmpty(pncnum))
            return null;
        String[] ss = pncnum.split("_");
        if (ss.length > 1)
            return ss[0];
        return null;
    }

    // 保留两位小数 -> string
    public static String formatBigDec(BigDecimal d) {
        if (d == null || d.doubleValue() == 0)
            return "0.00";
        return df1.format(d);
    }

    // 保留两位小数 -> string
    public static String formatBigDec(String s) {
        if (StringUtils.isEmpty(s))
            return "0.00";
        BigDecimal d = new BigDecimal(s);
        return formatBigDec(d);
    }

    /**
     * 版本号比较
     *
     * @param baseVersion   基础版本号
     * @param targetVersion 目前版本号
     * @return
     */
    public static boolean isLowerVersion(String baseVersion, String targetVersion) {
        boolean flag = false;
        int l1 = targetVersion.split("\\.").length;
        int l2 = baseVersion.split("\\.").length;
        int max_len = l1 > l2 ? l1 : l2;
        long s1 = getVersionSum(targetVersion, max_len);
        long s2 = getVersionSum(baseVersion, max_len);
        if (s1 < s2)
            flag = true;
        return flag;
    }

    private static long getVersionSum(String ver, int max_len) {
        long l = 0L;
        if (!StringUtils.isEmpty(ver)) {
            String[] vv = ver.split("\\.");
            int len_vv = vv.length;
            String[] tt = new String[max_len];
            for (int i = 0; i < max_len; i++) {
                String v = "0";
                if (i < len_vv)
                    v = vv[i];
                tt[i] = v;
            }
            String l_s = "1";
            for (String t : tt) {
                l_s += getLenPer(t);
            }
            l = Long.parseLong(l_s);
        }
        return l;
    }

    private static String getLenPer(String t) {
        String s = "";
        int i = Integer.parseInt(t);
        if (i < 10) {
            s = "00" + t;
        } else if (i < 100) {
            s = "0" + t;
        } else if (i < 1000) {
            s = t;
        } else {
            s = "000";
        }
        return s;
    }

    /**
     * 随机生成六位数验证码
     *
     * @return
     */
    public static int getRandomNum() {
        Random r = new Random();
        return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
    }

    /**
     * 随机生成六位密码
     *
     * @return
     */
    public static String getRandomPassword() {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < 6; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 截取部分url，app使用
     *
     * @param showimg
     * @return
     */
    public static String getAppShowImage(String showimg) {
        if (StringUtils.isEmpty(showimg))
            return "";
        if (showimg.startsWith("http")) {
            int p1 = showimg.indexOf("/upload");
            showimg = showimg.substring(p1);
        }
        return showimg;
    }

    public static String equalsBigDec(String s) {
        String price = "";
        BigDecimal b = new BigDecimal(s);
        int i = b.compareTo(BigDecimal.ZERO);
        if (i == -1) {
            price = "-¥" + b.toString().replace("-", "");
        } else if (i == 0) {
            price = "+¥" + b.toString();
        } else if (i == 1) {
            price = "+¥" + b.toString();
        }
        return price;
    }

    public static String[] str2StrArray(String str, String splitRegex) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return str.split(splitRegex);
    }

    public static String[] str2StrArray(String str) {
        return str2StrArray(str, ",");
    }

    //自定义
    //判断是否存在
    public static boolean equalBool(String str) {
        Boolean flag = false;
        if (str != null && !str.equals("")) {
            flag = true;
        }
        return flag;
    }

    public static boolean equalBool(Date date) {
        Boolean flag = false;
        if (date != null) {
            flag = true;
        }
        return flag;
    }

    public static boolean equalBool(Integer integer) {
        Boolean flag = false;
        if (integer == -1) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    public static boolean equalBool(Long long1) {
        Boolean flag = false;
        if (long1 != null) {
            flag = true;
        }
        return flag;
    }

    public static boolean equalBool(BigDecimal bigDecimal) {
        Boolean flag = false;
        if (bigDecimal != null) {
            flag = true;
        }
        return flag;
    }
}
