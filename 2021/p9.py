from util import get_file_contents


def adjacent(r,c,m):
    left = c-1
    right = c+1
    above = r-1
    below = r+1
    adj = []
    if 0 <= left < len(m[r]):
        adj.append(m[r][left])
    if 0 <= right < len(m[r]):
        adj.append(m[r][right])
    if 0 <= above < len(m):
        adj.append(m[above][c])
    if 0 <= below < len(m):
        adj.append(m[below][c])
    return adj

lines = get_file_contents('p9.txt')
heightmap = []
for line in lines:
    heightmap.append([int(x) for x in list(line)])

risk_levels = 0
for i in range(len(heightmap)):
    for j in range(len(heightmap[i])):
        adj = adjacent(i, j, heightmap)

        if heightmap[i][j] < min(adj):
            #print(f'low point: {heightmap[i][j]}')
            risk_levels += 1 + heightmap[i][j]


print(f'Day 1 part 1: {risk_levels}')







        

