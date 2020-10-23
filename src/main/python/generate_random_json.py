import json
import math
import string
import random
import argparse

def parse_input():
    parser = argparse.ArgumentParser(
        description="Random JSON Dataset Generator"
    )

    parser.add_argument("--size", default=30, type=int,
                        help="The Size of Data Item")
    parser.add_argument("--average-atomic-value-length", default=5, type=int,
                        help="The Average Atomic Value Length")
    parser.add_argument("--average-list-length", default=5, type=int, 
                        help="The Average List Length")
    parser.add_argument("--average-object-size", default=5, type=int,
                        help="The Average Object Size")
    parser.add_argument("--max-level", default=10, type=int, 
                        help="The Max Level of JSON Tree")

    ret = vars(parser.parse_args())
    ret["p_object"] = 0.5
    ret["p_list"] = 0.9
    ret["p_atomic"] = 0.1
    return ret

params = parse_input()

def generate_atomic_value(length):
    data = [random.choice(string.ascii_letters + string.digits) for _ in range(length)]
    return "".join(data)

def generate(level, params):
    s = random.random()
    if level == params["max_level"] or s > params['p_list']:
        return generate_atomic_value(params["average_atomic_value_length"])
    if s < params['p_object']:
        ret = dict()
        size = params["average_object_size"]
        for property in random.sample(string.ascii_lowercase, size - 1):
            ret[property] = generate(level + 1, params)
        return ret
    
    ret = list()
    size = params["average_list_length"]
    for _ in range(size):
        ret.append(generate(level + 1, params))
    return ret

def generate_special(level, params):
    if level == params["max_level"]:
        return generate_atomic_value(params["average_atomic_value_length"])
    return {chr(ord("a") + level): generate_special(level+1, params)}

if __name__ == '__main__':
    data = []
    params = parse_input()

    data.append(generate_special(0, params))
    for _ in range(params["size"]):
        s = random.random()
        level = 0
        if s > 0.1:
            level = random.randint(1, params["max_level"] - 2)
        data.append(generate(level, params))
    with open("random.json", "w") as f:
        json.dump(data, f, indent=2)