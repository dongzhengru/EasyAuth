package top.zhengru.sso.unified.utils;

import java.util.Random;

public class RandomStringUtil {
    // 字符串包含小写字母、大写字母和数字
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUM_CHARACTERS = "0123456789";
    private static final Random RANDOM = new Random(); // 随机数生成器

    /**
     * 生成随机字符串
     * @param length 需要生成的字符串长度
     * @return 随机生成的字符串
     */
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length()); 
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString(); 
    }

    /**
     * 生成随机数字
     *
     * @param length 需要生成的数字长度
     * @return 随机生成的数字
     */
    public static Long generateRandomNum(int length) {
        StringBuilder sb = new StringBuilder();
        int firstDigitIndex = RANDOM.nextInt(NUM_CHARACTERS.length() - 1) + 1;
        sb.append(NUM_CHARACTERS.charAt(firstDigitIndex));
        for (int i = 1; i < length; i++) {
            int index = RANDOM.nextInt(NUM_CHARACTERS.length());
            sb.append(NUM_CHARACTERS.charAt(index));
        }
        return Long.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        String randomString = RandomStringUtil.generateRandomString(18);
        System.out.println("生成的随机字符串: " + randomString);

        Long randomNum = RandomStringUtil.generateRandomNum(18);
        System.out.println("生成的随机数字: " + randomNum);
    }
}