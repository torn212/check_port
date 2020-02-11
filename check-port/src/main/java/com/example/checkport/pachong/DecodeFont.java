package com.example.checkport.pachong;

import org.apache.commons.codec.binary.Base64;
import sun.font.Font2D;
import sun.font.Font2DHandle;
import sun.font.TrueTypeFont;
import sun.font.TrueTypeGlyphMapper;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;

public class DecodeFont {

    public static void main(String[] args) {
        System.out.println(decodeString("", "宗層编号sadf撒旦法"));
        System.out.println(decodeString("", "象圊县讓目濕軍挾松兰讓道臘巨鹰路交叉口斷錁角"));
        System.out.println(decodeString("", "宗層编号"));
        System.out.println(decodeString("", "宗層编号"));
    }
    /**
     * font-secret字符串专用解密工具
     *
     * @param key          密匙
     * @param encodeString 加密后的字符串
     * @return 解密后的字符串
     */
    public static String decodeString(String key, String encodeString) {
        try {
            //base64解码，初始化字体
            byte[] ss = Base64.decodeBase64(key);
            InputStream inputStream = new ByteArrayInputStream(ss);
//            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/likun/Downloads/my.ttf"));
            FontRenderContext fontRenderContext = new FontRenderContext(new AffineTransform(), false, false);
            GlyphVector glyphVector = dynamicFont.createGlyphVector(fontRenderContext, "");

            //获取font中字形的映射关系，字段为private，使用反射
            Class<?> clazz = Font.class;
            Field[] fs = clazz.getDeclaredFields();
            Font2DHandle font2DHandle = null;
            for (int i = 0; i < fs.length; i++) {
                fs[i].setAccessible(true);// 将目标属性设置为可以访问
                if (fs[i].getName().equals("font2DHandle")) {
                    font2DHandle = (Font2DHandle) fs[i].get(dynamicFont);
                }

            }

            //得到映射关系
            Font2D font2D = font2DHandle.font2D;
            TrueTypeFont trueTypeFont = (TrueTypeFont) font2D;
            TrueTypeGlyphMapper charToGlyphMapper = (TrueTypeGlyphMapper) trueTypeFont.getMapper();

            //开始解密，encodeString为加密后的字符串
            StringBuffer buffer = new StringBuffer();
            char[] chars = encodeString.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                buffer.append(charToGlyphMapper.charToGlyph(chars[i]) - 1);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
