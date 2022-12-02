import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class File {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("d:/FileByte.txt");
        Charset charset = StandardCharsets.US_ASCII;
        String s = parse(inputStream, charset);
        System.out.println(s);
    }

    private static String parse(InputStream inputStream, Charset charset) throws IOException {
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String[] strings = new String(bytes, charset).split(" ");
        bytes = new byte[strings.length];
        for(int i = 0; i < strings.length; i++) bytes[i] = Byte.parseByte(strings[i]);
        return new String(bytes, charset);
    }
}

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        while(inputStream.available() > 0){
//            int data = inputStream.read();
//            if(data != 32) baos.write(data);
//            if(baos.toByteArray().length == 2){
//                res += new String(new byte[]{Byte.parseByte(String.valueOf(baos))},charset);
//                baos.reset();
//            }
//        }

//    byte[] b = new byte[]{(byte) inputStream.read()};
//            if(b[0] != 32) s += new String(b, charset);
//                    if(s.length() == 2){
//                    res += new String(new byte[]{Byte.parseByte(s)}, charset);
//                    s = "";
//                    }

//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes);
//        String[] s = new String(bytes, charset).split(" ");
//        for (String value : s)
//            System.out.print(new String(new byte[]{Byte.parseByte(value)}, charset));

//        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\123.txt"), charset));
//        String s = new String();
//        while(inputStream.available() > 0){
//            char c = (char) inputStream.read();
//            if(c != ' ') s += c;
//            if(s.length() > 1){
//                out.write(Byte.parseByte(s));
//                s = new String();
//            }
//        }
//        out.close();

//        String[] bufferedReader = new BufferedReader(new InputStreamReader(inputStream)).readLine().split(" ");
//        for (String s : bufferedReader)
//            out.write(Byte.parseByte(s));
//        out.close();