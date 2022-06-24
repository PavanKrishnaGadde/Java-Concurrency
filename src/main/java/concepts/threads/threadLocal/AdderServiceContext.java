package concepts.threads.threadLocal;

public class AdderServiceContext {
    public static ThreadLocal<AdderService> threadLocal = new ThreadLocal<AdderService>(){

        @Override
        protected AdderService initialValue() {
            return new AdderService();
        }

        @Override
        public AdderService get() {
            return super.get();
        }
    };

    public static ThreadLocal<String> userId = new ThreadLocal<>();

    public static String commonVar = "";
}
