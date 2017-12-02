/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplina: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Página 118 Thomas H. Cormen 3a Ed 
 *
 * Fila de Prioridade Mãxima/Max-Priority Queue
 *
 * Atenção:
 * Vetor em java inicia em 0, os algoritmos consideram início em 1.
 * A subtração de -1 ocorre somente no local de acesso ao vetor ou matriz 
 * para manter a compatibilidade entre os algoritmos.
 *
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    //Vetor que armazena os dados da fila de prioridade
    static int[] S;
    //Quantidade de elementos da fila de prioridade
    static int n;

    /**
     * Pai ou Parent.
     * 
     * Retorna o índice do pai de i.
     *
     * Página 111 Thomas H. Cormen 3 ed
     *
     * @param i Índice do filho
     * @return O índice do seu pai
     */
    public static int pai(int i) {
        return (i / 2);
    }

    /**
     * Retorna o índice do filho da esquerda de i.
     *
     * Página 111 Thomas H. Cormen 3 ed
     *
     * @param i Ïndice do pai
     * @return O índice do filho da esquerda de i
     */
    public static int esquerda(int i) {
        return (2 * i);
    }

    /**
     * Retorna o índice do filho da direita de i.
     *
     * Página 111 Thomas H. Cormen 3 ed
     *
     * @param i Ïndice do pai
     * @return O índice do filho da direita de i
     */
    public static int direita(int i) {
        return (2 * i + 1);
    }

    /**
     * Realiza a troca de posição de dois elementos do vetor.
     *
     * @param A Vetor que contém os dados
     * @param i Primeira posição de torca
     * @param j Segunda posição de torca
     */
    public static void troca(int[] A, int i, int j) {
        int aux = A[i - 1];
        A[i - 1] = A[j - 1];
        A[j - 1] = aux;
    }

    /**
     * Inicializa o vetor e dados da fila.
     */
    public static void inicializacaoFila() {
        S = new int[100];
        n = 0;
    }

    /**
     * Maior ou Maximum.
     *
     * Retorna o maior elemento da fila.
     *
     * @return O maior elemento.
     */
    public static int maior() {
        return S[1 - 1];
    }

    /**
     * Inserir ou Insert.
     *
     * @param x valor a ser inserido na fila
     */
    public static void inserir(int x) {
        n = n + 1;
        S[n - 1] = Integer.MIN_VALUE;
        aumentarChave(n,x);
    }

    /**
     * MaxHeapiFy. 
     * 
     * Recebe A e i >= 1 tais que subárvores com raízes 2i e 2i + 1.
     * São max-heaps e rearranja A de modo que subárvore com raiz i seja um
     * max-heap. Organiza os elementos da heap, coloca o maior na raiz.
     *
     * Página 112 Thomas H. Cormen 3 ed
     *
     * @param A Vetor a ser odenado
     * @param n Quantidade de elementos do vetor
     * @param i Representa a posição do nó Raiz da árvore
     */
    private static void maxHeapify(int A[], int n, int i) {
        //Armazena o maior elemento
        int maior = 0;
        //Filho da esquerda
        int esquerda = esquerda(i);                             //Theta(1)
        //Filho da direita
        int direita = direita(i);                               //Theta(1)
        if ((esquerda <= n) && (A[esquerda - 1] > A[i - 1])) {  //Theta(1)
            maior = esquerda;                                   //O(1)
        } else {
            maior = i;                                          //O(1)
        }
        if ((direita <= n) && (A[direita - 1] > A[maior - 1])) {//Theta(1)
            maior = direita;                                    //O(1)
        }
        if (maior != i) {                                       //Theta(1)
            troca(A, i, maior);                                 //O(1)
            maxHeapify(A, n, maior);                            //Theta(h-1)
        }
    }

    /**
     * Extrair o maior da fila de prioridade.
     *
     * Retorna o maior elemento da fila de prioridade.
     *
     * @return O elemento da fila de prioridade.
     */
    public static int extrairMaior() {
        int maior;
        if (n < 1) {
            System.out.println("Fila de prioridade vazia");
            maior = 0;
        } else {
        maior = S[1 - 1];
        S[1 - 1] = S[n - 1];
        n = n - 1;
        maxHeapify(S, n, 1);
        
        }
        return maior;
    }

    /**
     * Aumenta o valor do elemento x para o valor k.
     *
     * @param x Posição a ser substituída.
     * @param k Valor para a posição
     */
    public static void aumentarChave(int x, int k) {
        if (k < S[x]) {
            System.out.println("Erro, novo valor é menor que o atual.");
        }
        S[x-1] = k;
        while ((x > 1) && S[pai(x)-1] <S[x-1]){
            troca(S,x,pai(x));            
            x = pai(x);
        }   
    }

    public static void main(String args[]) {

        //Inicializa o vetor utilizado na fila    
        inicializacaoFila();

        //Vetor de Dados          
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};

        System.out.println(">>> FFila de Prioridade Mãxima/Max-Priority Queue <<<");

        for (int i = 1; i <= A.length; i++) {
            System.out.println("Emfileirando:" + A[i-1]);
            inserir(A[i-1]);
        }

        System.out.println("\nDados na fila");
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "=>" + S[i-1]);
        }

        System.out.println("\nDesemfileirando:" + extrairMaior());
        System.out.println("Dados na fila");
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "=>" + S[i-1]);
        }

        System.out.println("\nDesemfileirando:" + extrairMaior());
        System.out.println("Dados na fila");
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "=>" + S[i-1]);
        }
        
         System.out.println("\nDesemfileirando:" + extrairMaior());
        System.out.println("Dados na fila");
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "=>" + S[i-1]);
        }
    }
}