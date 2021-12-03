from collections import Counter
from statistics import multimode
from util import get_file_contents

lines = get_file_contents('p3.txt')


def find_most_common(l):
    modes = multimode(l)
    return modes[0] if len(modes) == 1 else 1


def find_least_common(l):
    occurrences = Counter(l)
    anti_modes = [k for k in occurrences
                  if all(occurrences[temp] >= occurrences[k] for temp in occurrences)]
    return anti_modes[0] if len(anti_modes) == 1 else 0


def build_bit_frequencies(lines):
    d = {}
    for i in range(len(lines)):
        bits = list(lines[i])
        for j in range(len(bits)):
            if d.get(j):
                d[j].append(int(bits[j]))
            else:
                d[j] = [int(bits[j])]
    return d


d = build_bit_frequencies(lines)
gamma = ''
for k, v in d.items():
    gamma += str(find_most_common(v))

epsilon = ''
for k, v in d.items():
    epsilon += str(find_least_common(v))

print(f'Day 3 part 1: {gamma}, {epsilon} {int(gamma,2) * int(epsilon, 2) }')

oxg = lines.copy()
i = 0
while len(oxg) > 1:
    freqs = build_bit_frequencies(oxg)
    mc = find_most_common(freqs[i])
    oxg = list(filter(lambda l: l[i] == str(mc), oxg))
    i += 1

co2 = lines.copy()
i = 0
while len(co2) > 1:
    freqs = build_bit_frequencies(co2)
    lc = find_least_common(freqs[i])
    co2 = list(filter(lambda l: l[i] == str(lc), co2))
    i += 1


print(f'Day 3 part 1: {oxg[0]}, {co2[0]} {int(oxg[0],2) * int(co2[0], 2) }')