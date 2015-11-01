package com.kyee.framework.security.service.token;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 程峰
 * 创建时间：15/8/14 下午5:12
 * 任务号：MOBILEDEVELOP-10034
 * 创建说明：token生成和解析接口
 */
public abstract class ITokenService {

    private static final String DELIMITER = ":";
    /**
     * 通过用户信息生成token
     * */
    abstract public String parseTokenFromUser(String username,HttpServletResponse response);

    /**
     * 根据token解析用户信息
     * */
    abstract public UserDetails parseUserFromToken(String token,HttpServletResponse response);

    /**
     * 对token通过DELIMITER进行分隔后进行编码
     * @param tokens 需要被编码的信息
     **/
    protected String encodeToken(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < tokens.length; i++) {
            sb.append(tokens[i]);

            if (i < tokens.length - 1) {
                sb.append(DELIMITER);
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') { //去掉Base64编码后面的=
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    /**
     * 解码token
     * @param token token
     * @return 解码token String[]
     * */
    protected String[] decodeToken(String token) {
        if("".equals(token)){
            return new String[]{null};
        }
        for (int j = 0; j < token.length() % 4; j++) {
            token = token + "=";    //补全Base64编码后面的=
        }

        if (!Base64.isBase64(token.getBytes())) {
            return new String []{null};
        }

        String cookieAsPlainText = new String(Base64.decode(token.getBytes()));

       return StringUtils.delimitedListToStringArray(cookieAsPlainText, DELIMITER);

    }
}
