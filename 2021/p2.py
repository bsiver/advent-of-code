from util import get_file_contents
split_lines = [line.split(' ') for line in get_file_contents('p2.txt')]

x = 0
y = 0
for k, v in split_lines:
    v = int(v)
    if k == 'forward':
        x += v
    elif k == 'down':
        y += v
    elif k == 'up':
        y -= v

print(f'Day 2 ({x}, {y}): {x*y}')
