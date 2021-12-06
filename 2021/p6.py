from util import get_file_contents

fish = [int(x) for x in get_file_contents('p6-ex.txt')[0].split(',')]

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



print(f'After 80 days len: {len(part_one(80))}')




        

