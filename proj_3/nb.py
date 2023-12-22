#!/usr/bin/env python
# coding: utf-8

# Iwo Szczepaniak

# ### IDEA

import graphviz, sys

# TESTY

# Wizualizacje są renderowane i zapisywane podczas testu w plikach .png

def get_input(file):
    with open(file, 'r') as f:
        n = f.readline().strip()
        transactions = []
        for line in f:
            if line[0]!='#':
                transactions.append(line.split())
    matrix_core = transactions[:-1]
    new_col = transactions[-1]
    # add new column to matrix
    for i in range(len(matrix_core)):
        matrix_core[i].append(new_col[i])
    return int(n), len(matrix_core[0]), matrix_core

def calc_alpabet(n, m):
    alph = []
    for i in range(0, n):
        for k in range(i+1, n):
            alph.append(f"A_{i}_*_{k}")
            for j in range(i, m):
                alph.append(f"B_{i}_{j}_{k}")
                alph.append(f"C_{i}_{j}_{k}")
    return alph

def calc_transactions(alph):
    transactions = []
    for i,el_prev in enumerate(alph): 
        for k in range(i+1, len(alph)):
            el_next = alph[k]

            cond1 = (el_prev[1:] == el_next[1:]) # ex. B_1,1,2 -> C_1,1,2
            cond2 = (el_prev[4]==el_next[2] and el_prev[2] != el_next[2] and el_prev[0] != 'B') # ex. C_1,2,2 -> A_2,3   
            cond3 = (el_prev[4] == "*" and el_prev[2] == el_next[2] and el_prev[6] == el_next[6] and el_next[0] == 'B') # ex. A_1,2->B_1,1,2

            if  cond1 or cond2 or cond3:                                                         
                if f"{el_prev} <- {el_next}" not in transactions:
                    transactions.append(f"{el_next} <- {el_prev}")
                if cond2: # makes it possible not to draw too many lines as in class
                    break
    return transactions

def create_graph(transactions):
    D = dict()
    G = dict()
    for transaction in transactions:
        prev, next = transaction.split(" <- ")
        if prev not in D:
            D[prev] = []
            G[prev] = []
        if next not in D:
            D[next] = []
            G[next] = []
        D[prev].append(next)
        G[next].append(prev)
    return D, G

def vis_graph(graph, extr_str = ""):
    dot = graphviz.Digraph(comment='The Round Table')
    for key in graph:
        dot.node(key)
        for val in graph[key]:
            dot.edge(key, val)
    dot.format = 'png'
    dot.render(filename=f"{extr_str}graph_size_{n}", view=True)

def calculate_vals(graph, start_point:str, vals:dict):
    round_nr = 0
    queue = [(start_point, round_nr)]

    while queue:
        current_node, round_nr = queue.pop(0)
        vals[current_node] = max(vals[current_node],round_nr)
        for val in graph[current_node]:
            queue.append((val, round_nr + 1))
    return vals

def find_diff_edges(graph, vals):
    diff_edges = []

    for key in graph:
        for nei in graph[key]:
            if abs(vals[key] - vals[nei]) != 1:
                diff_edges.append((key, nei))

    return diff_edges

def find_cycles(graph, vals):
    cycles = []
    for key in graph:
        for nei in graph[key]:
            graph[key].remove(nei)
            if abs(vals[key] - vals[nei]) == 1:
                graph[key].append(nei)
    return cycles

def calculate_foata(graph, vals):
    max_val = max(vals.values())
    foata = [[] for _ in range(max_val + 1)]
    for val in range(max_val + 1):
        for key in graph:
            if vals[key] == val:
               foata[val].append(key)
    return foata

if __name__ == "__main__":
    if len(sys.argv) == 2:
        input_file = sys.argv[1]
    else:
        input_file = "input_.txt"

    n, m, matrix = get_input(input_file)

    alph = calc_alpabet(n, m)

    transactions = calc_transactions(alph)

    dep, graph = create_graph(transactions)

    vis_graph(graph) # before deletation

    vals = {key: 0 for key in graph}
    for key in graph:
        if dep[key] == []:
            vals = calculate_vals(graph, key, vals)

    pot = find_diff_edges(graph, vals)

    find_cycles(graph, vals)
    vis_graph(graph, "deleted_edges_") # after deletation

    foata = calculate_foata(graph, vals)
    
    print(f"Foata is:")
    for f_class in foata:
        print(f_class)

    with open("foata.txt", "w") as file:
        for f_class in foata:
            for el in f_class:
                file.write(el)
                file.write(" ")
            file.write("\n")
