from statistics import median
from util import get_file_contents

positions = [int(x) for x in get_file_contents('p7.txt')[0].split(',')]

m = median(positions)
distances = [abs(pos-m) for pos in positions]
fuel_spent = sum(distances)

print(f'Day 7 part 1: alignment position: {m} | {fuel_spent}')





        

