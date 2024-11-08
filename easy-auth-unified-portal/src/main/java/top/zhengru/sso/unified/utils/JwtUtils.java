package top.zhengru.sso.unified.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtils {
    /*
    iss: jwt签发者
    sub: jwt所面向的用户
    aud: 接收jwt的一方
    exp: jwt的过期时间，这个过期时间必须要大于签发时间
    nbf: 定义在什么时间之前，该jwt都是不可用的.
    iat: jwt的签发时间
    jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
    */

    //加密算法
    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    //私钥 / 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取，切记这个秘钥不能外露，只在服务端使用，在任何场景都不应该流露出去。
    // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
    public final static String SECRET = "";

    // 过期时间（单位秒）/ 2小时
    private final static Long ACCESS_TOKEN_EXPIRATION = 3600 * 12L;

    //jwt签发者
    private final static String JWT_ISS = "Easy-Auth-Unified-Portal";

    //jwt所有人
    private final static String SUBJECT = "all";

    //请求头的name
    public static final String HEADER_TOKEN_NAME = "Authorization";

    /**
     * 创建jwt
     *
     * @return 返回生成的jwt token
     */
    public String generateJwtToken(Map<String, Object> claims) {

        // 头部 map / Jwt的头部承载，第一部分
        // 可不设置 默认格式是{"alg":"HS256"}
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        //标准中注册的声明 (建议但不强制使用)
        //一旦写标准声明赋值之后，就会覆盖了那些标准的声明
        claims.put("iss", JWT_ISS);
            /*	iss: jwt签发者
                sub: jwt所面向的用户
                aud: 接收jwt的一方
                exp: jwt的过期时间，这个过期时间必须要大于签发时间
                nbf: 定义在什么时间之前，该jwt都是不可用的.
                iat: jwt的签发时间
                jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
            */


        //下面就是在为payload添加各种标准声明和私有声明了
        return Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setHeader(map)         // 头部信息
                .setClaims(claims)      // 载荷信息
                .setId(UUID.randomUUID().toString()) // 设置jti(JWT ID)：是JWT的唯一标识，从而回避重放攻击。
                .setIssuedAt(new Date())       // 设置iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION * 1000)) // 设置exp：jwt过期时间
                .setSubject(SUBJECT)    //设置sub：代表这个jwt所面向的用户，所有人
                .signWith(SIGNATURE_ALGORITHM, SECRET)//设置签名：通过签名算法和秘钥生成签名
                .compact(); // 开始压缩为xxxxx.yyyyy.zzzzz 格式的jwt token
    }
}
