from statistics import median
from util import get_file_contents

def part_one(positions):
    m = median(positions)
    distances = [abs(pos-m) for pos in positions]
    fuel_spent = sum(distances)
    return m, fuel_spent


def part_two(positions):
    # iterate over all positions, determine min cost for all to align there
    min_cost = None
    best_pos = 0
    for i in range(0, max(positions)):
        costs = []
        for j in range(0, len(positions)):
            start = abs(positions[j] - i)
            # sum(1, n) = n(n+1)/2
            cost = (start * (start+1)) / 2
            costs.append(cost)
        if not min_cost or sum(costs) < min_cost:
            min_cost = sum(costs)
            best_pos = i
    return best_pos, min_cost

positions = [int(x) for x in get_file_contents('p7.txt')[0].split(',')]
pos, fuel_spent = part_one(positions)
print(f'Day 7 part 1: alignment position: {pos} | {fuel_spent}')

pos, fuel_spent = part_two(positions)
print(f'Day 7 part 2: alignment position: {pos} | {fuel_spent}')





        

