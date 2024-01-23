package com.al.easyexcel.test;

public class Test {
    public static void main(String[] args) throws Exception {

        /*String filePath = "D:\\\\下载等\\\\桌面\\\\新建 文本文档 (4).txt";
        byte[] bytes = Files.readAllBytes (Paths.get (filePath));
        UniversalDetector universalDetector = new UniversalDetector (null);
        universalDetector.handleData (bytes,0,bytes.length);
        universalDetector.dataEnd ();
        String encoding = universalDetector.getDetectedCharset ();
        System.out.println (encoding);
        Charset charset = encoding != null ? Charset.forName(encoding) : StandardCharsets.UTF_8;
        String content = new String(bytes, charset);*/

        /*String filePath = "D:\\下载等\\桌面\\新建 文本文档 (4).txt";
        InputStream inputStream = new FileInputStream (filePath);
        Charset detect = CharsetDetector.detect (new File (filePath));
        System.out.println (detect);
        try {
            BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream,detect));
            String line;
            while((line = bufferedReader.readLine ())!= null){
                System.out.println (line);
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }*/
        /*int size = 10000000;
        long l = System.currentTimeMillis ();
        List<String> list = new ArrayList<> ();
        while (list.size () < size) {
            String s = UUID.randomUUID ().toString ().replaceAll ("-", "").substring (0, 5);
            list.add (s);
        }
        System.out.println (list.size ());
        long l2 = System.currentTimeMillis ();
        System.out.println (l2 - l);
        for ( int i = 0; i < 20; i++){
            System.out.println (list.get (i));
        }
        HashSet<String> strings = new HashSet<> (list);
        System.out.println (strings.size () - list.size ());
    }*/

       /* UniqueStringGenerator generator = new UniqueStringGenerator ();

        List<String> list = new ArrayList<> ();
        for ( int i = 0; i < 10000000; i++ ) {
            String s = generator.generateUniqueString ();
            list.add (s);
        }
        HashSet<String> strings = new HashSet<> (list);
        System.out.println (strings.size ());
        System.out.println (list.size ());
    }*/
        //不能使用is前缀，部分框架会导致序列化异常

        String a = "a;b;c;;";
        String[] split = a.split (";");
        System.out.println (split.length);
    }
}
