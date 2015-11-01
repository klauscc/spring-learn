package com.kyee.framework.wechat.bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author 夏之阳
 * 创建时间：2015-08-11 10:33
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：微信验证签名
 */

public class CheckSignature {
    /**
     * 微信端验证服务端配置的token，修改人：叶丹，任务号：MOBILEDEVELOP-10267
     */
    private static String token  = "token";

    /**
     * 验证微信加密签名
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return 签名是否有效
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] array = new String[] { token, timestamp, nonce};
        Arrays.sort(array);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();
        // SHA1签名生成
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return signature.equals(hexstr.toString());
    }
}
