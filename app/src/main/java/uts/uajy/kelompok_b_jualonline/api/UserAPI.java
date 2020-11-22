package uts.uajy.kelompok_b_jualonline.api;

public class UserAPI {
    public static final String ROOT_URL   = "https://180709738.000webhostapp.com/";
    public static final String ROOT_API   = ROOT_URL+ "api/";
    public static final String URL_IMAGE  = ROOT_URL+"images/";

    //    method POST create
    public static final String URL_REGISTER = ROOT_API + "user/register";

    //    method POST login
    public static final String URL_LOGIN = ROOT_API + "user/login";

    //    method GET by ID
    public static final String URL_GET = ROOT_API + "user/";

    //    method PUT
    public static final String URL_UPDATE = ROOT_API + "user/";

    //    method DELETE by ID
    public static final String URL_DELETE = ROOT_API + "user/";
}
