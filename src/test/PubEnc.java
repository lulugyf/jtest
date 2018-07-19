package test;


/*
翻译自源代码: /crmtux1/run/framework/pub/libbusi/publicBusi.cpp 中的下面这个函数
utype* pubEncryptPasswd(const utype *puInParam, TCtrlInfo *ptCtrlInfo)
{
        int i=0;
        int f=0;
        char* src=utGetStr(puInParam, "SRC");
        char dst[1024];
        uAutoPtr puo(uInit(1));
        const char* ptsr="PLMOKNIJBUHYGVTFCRDXESZAQWqazwsxedcrfvtgbyhnujmikolp1234567890JBUHYGVTFC-*&^$#@!;]";
        char* en=strchr(src, ' ');
        if(en) *en=0;
        size_t len=strlen(src);
        memset(ptCtrlInfo, 0 ,sizeof(TCtrlInfo));
        if(utGetInt(puInParam, "OP_TYPE")){
                for(i=0;src[i]!=0;++i){
                        //f=(src[i]-'0'+i+1)*(len/2+1);
                        //L4C_TRACE("\n--- BEGIN ****----f==[%d]",f);
                        dst[i]=ptsr[(src[i]-'0'+i+1)*(len/2+1)];
                }
        }else{
                for (i=0;i<len;++i){
                        const char* c=strchr(ptsr, src[i]);
                        if(c==NULL) dst[i]='0';
                        else dst[i]=((c-ptsr)/(len/2+1)-i-1)+'0';
                }
        }
        dst[i]=0;
        utAddStr(puo, "DST", dst);
        return puo.release();
}

 */
public class PubEnc {
	private final static String pstr = "PLMOKNIJBUHYGVTFCRDXESZAQWqazwsxedcrfvtgbyhnujmikolp1234567890JBUHYGVTFC-*&^$#@!;]";
	public static String encode(String src, boolean enc) {
		char[] s = src.toCharArray();
		char[] d = new char[s.length];
		int len = s.length;
		if(enc) {
			for(int i=0; i<len; i++) {
				d[i] = pstr.charAt((s[i]-'0'+i+1)*(len/2+1));
			}
		}else {
			for(int i=0; i<len; i++) {
				int x = pstr.indexOf(s[i]);
				if(x < 0) d[i] = '0';
				else d[i] = (char )((x/(len/2+1)-i-1)+'0');
			}
		}
		return new String(d);
	}
	
	public static void main(String[] args) {
		System.out.println(encode("123456", true));
		System.out.println(encode("BCQebk", false));
	}

}
