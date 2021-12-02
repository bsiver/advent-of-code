def get_file_contents(fname):
    with open(f'resources/{fname}') as f:
        lines = f.readlines()
        lines = [(line.rstrip()) for line in lines]
    return lines
