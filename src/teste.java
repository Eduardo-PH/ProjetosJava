public class teste {
    public static void main(String[] args) {

        int [] numeros = {1, 2, 3, 4, 5};
        int soma = 0;

        try {
            for (int i = 0; i <= 5; i = i + 1) {
                soma += numeros[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            soma = -1;
        } catch (Exception e) {
            soma = 0;
        } finally {
            soma = soma * 2;
        }

        System.out.println(soma);

    }
}
