public class Main {
    public static void main(String[] args) {
        Window window = new Window();

        window.texture = DSNoise.generate(0, 50, 50);
    }
}
