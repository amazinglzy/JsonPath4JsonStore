import os
import json

files = os.listdir("mge/templates")
output = "merged.json"

parts = list()
for file in files:
    with open("mge/templates/" + file, encoding='utf-8') as f:
        t = json.load(f)
        parts += t["data"]

with open("mge/" + output, "w", encoding='utf-8') as f:
    json.dump(parts, f, ensure_ascii=False, indent=2)
