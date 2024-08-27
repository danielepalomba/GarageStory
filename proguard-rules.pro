-keep class com.app.** { *; }
-dontwarn com.app.*
# Offusca solo la classe Main, mantenendo il metodo main non offuscato
-keep class com.app.Main {
    public static void main(java.lang.String[]);
}