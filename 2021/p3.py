from util import get_file_contents
lines = get_file_contents('p3.txt')


def find_most_common(l):
    return max(set(l), key=l.count)


def find_least_common(l):
    return min(set(l), key=l.count)

d = {}
for i in range(len(lines)):
    bits = list(lines[i])
    for j in range(len(bits)):
        if d.get(j):
            d[j].append(int(bits[j]))
        else:
            d[j] = [int(bits[j])]

gamma = ''
for k, v in d.items():
    gamma += str(find_most_common(v))

epsilon = ''
for k, v in d.items():
    epsilon += str(find_least_common(v))

print(f'Day 3 part 1: {gamma}, {epsilon} {int(gamma,2) * int(epsilon, 2) }')