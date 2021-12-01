def get_file_contents(fname):
    with open(f'resources/{fname}') as f:
        lines = f.readlines()
        lines = [(line.rstrip()) for line in lines]
    return lines


lines = [int(x) for x in get_file_contents('p1.txt')]
s = 0
for i in range(1, len(lines)):
    if lines[i] > lines[i-1]:
        s += 1

print(f'Day 1 part 1: {s}')


lines = [int(x) for x in get_file_contents('p1.txt')]
sums = []
for i in range(len(lines) - 2):
    window = lines[i: i + 3]
    sums.append(sum(window))

s = 0
for i in range(1, len(sums)):
    if sums[i] > sums[i-1]:
        s += 1
print(f'Day 1 part 2: {s}')