package aplicacao_java;

public class Main {
	public static void main(String[] args) {
        String csvFile = "caminho/do/arquivo.csv";
        List<Produto> produtos = new ArrayList<>();

        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String nome = data[1];
                double preco = Double.parseDouble(data[2]);
                int quantidade = Integer.parseInt(data[3]);
                produtos.add(new Produto(id, nome, preco, quantidade));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Operações funcionais
        // Encontrar o produto mais caro
        OptionalDouble maxPrice = produtos.stream().mapToDouble(Produto::getPreco).max();
        maxPrice.ifPresent(value -> System.out.println("Preço máximo: " + value));

        // Calcular o valor total em estoque
        double totalValue = produtos.stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
        System.out.println("Valor total em estoque: " + totalValue);

}
