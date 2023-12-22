
def input(file):
    with open(file, 'r') as f:
        n = f.readline().strip()
        transactions = []
        for line in f:
            transactions.append(line.split())
    matrix_core = transactions[:-1]
    new_col = transactions[-1]
    # add new column to matrix
    for i in range(len(matrix_core)):
        matrix_core[i].append(new_col[i])
    return int(n), len(matrix_core[0]), matrix_core

input_file = "input.txt"
n, m, matrix = input(input_file)

print(f"n is: {n}")
print(f"Matrix is:\n{matrix}")

indexing_1 = False
start = 0
if indexing_1:
    n+=1
    m+=1
    start = 1

trans_dependencies = []
for i in range(start, n):
    for k in range(i+1, n):
        trans_dependencies.append([])

alph = []
for i in range(start, n):
    for k in range(i+1, n):
        alph.append(f"A_{i}_*_{k}")
        trans_dependencies[i+k-1].append(f"A_{i}_*_{k}")
        for j in range(i, m):
            alph.append(f"B_{i}_{j}_{k}")
            alph.append(f"C_{i}_{j}_{k}")
            trans_dependencies[i+k-1].append(f"B_{i}_{j}_{k}")
            trans_dependencies[i+k-1].append(f"C_{i}_{j}_{k}")

print("Alphabet is:\n", alph)
print("Transactions_dependencies are: ")
for line in trans_dependencies: 
    print(line)

transactions = []
for i,el_prev in enumerate(alph): 
    for k in range(i+1, len(alph)):
        el_next = alph[k]
        # if (el_prev != el_next) and ( (el_prev[1:] == el_next[1:]) or (el_prev[4]==el_next[2] and el_prev[2] != el_next[2] and el_prev[0] != 'B') or(el_prev[4] == "*" and el_prev[2] == el_next[2] and el_prev[6] == el_next[6] and el_next[0] == 'B')):
        if (el_prev[1:] == el_next[1:]) or (el_prev[4]==el_next[2] and el_prev[2] != el_next[2] and el_prev[0] != 'B') or(el_prev[4] == "*" and el_prev[2] == el_next[2] and el_prev[6] == el_next[6] and el_next[0] == 'B'):
            # B_1,1,2 -> C_1,1,2,                        C_1,2,2 -> A_2,3                                                           A_1,2->B_1,1,2
            if f"{el_prev} <- {el_next}" not in transactions:
                transactions.append(f"{el_next} <- {el_prev}")
            
# el_prev[4] == line[k][4] or (el_prev[4] == '*' and line[k][0] == 'B' and el_prev[6] == line[k][6])

print(f"Transactions are: {transactions}")

