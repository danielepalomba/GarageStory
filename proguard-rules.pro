-dontwarn com.app.*
# Mantieni la classe Main, ma offusca i suoi membri tranne il metodo main
-keep class com.app.Main {
    public static void main(java.lang.String[]);
}
