import json
import math
import string
import random
import argparse

def parse_input():
    parser = argparse.ArgumentParser(
        description="Random JSON Dataset Generator"
    )

    parser.add_argument("--size", default=100000, type=int,
                        help="The Size of Data Item")
    parser.add_argument("--average-atomic-value-length", default=5, type=int,
                        help="The Average Atomic Value Length")
    parser.add_argument("--max-level", default=10, type=int, 
                        help="The Max Level of JSON Tree")

    ret = vars(parser.parse_args())
    return ret

params = parse_input()

if __name__ == '__main__':
    data = []
    params = parse_input()

    for _ in range(params["size"]):
        data.append({"a": []})


    current_level = data[:]
    for i in range(params["max_level"]):
        previous_char = string.ascii_letters[i]
        current_char = string.ascii_letters[i+1]
        update_level = []
        for _ in range(params["size"]):
            parent = random.choice(current_level)
            child = {current_char: []}
            parent[previous_char].append(child)
            update_level.append(child)
        current_level = update_level

    with open("random.json", "w") as f:
        json.dump(data, f, indent=2)