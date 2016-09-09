/**
 * Created by zhangmeng on 16-7-28.
 */
public class TStringToByte {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    public static void main(String[] args) throws Exception{
        String str = "测试h5充值";
        byte[] bytes = str.getBytes();
        System.out.println(bytesToHex(bytes));
    }
}
