from collections import Counter

from util import get_file_contents

fish = [int(x) for x in get_file_contents('p6.txt')[0].split(',')]

print(f'Initial state: {fish}')

def part_one(n):
    for i in range(1, n+1):
        for j in range(0, len(fish)):
            new_val = fish[j] - 1
            if new_val == 0:
                fish[j] = new_val
            elif fish[j] == 0:
                fish.append(8)
                fish[j] = 6
            else:
                fish[j] = new_val
    return fish

def part_two(n):
    f = Counter(fish)
    for i in range(n):
        new_fish_count = f[0]
        # increment all ages
        for i in range(8):
            f[i] = f[i + 1]
        f[8] = new_fish_count
        f[6] += new_fish_count
    return f



#print(f'After 80 days len: {len(part_one(80))}')

print(f'After 80 days len: {sum(part_two(256).values())}')




        

