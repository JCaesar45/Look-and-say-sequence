from __future__ import annotations

import re
from typing import Final

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field, field_validator

app = FastAPI(title="Aurum Sequence API", version="1.0.0")
DIGIT_PATTERN: Final = re.compile(r"^\d+$")


class SequenceRequest(BaseModel):
    value: str = Field(min_length=1, max_length=10_000)

    @field_validator("value")
    @classmethod
    def validate_digits(cls, value: str) -> str:
        if not DIGIT_PATTERN.match(value):
            raise ValueError("value must contain decimal digits only")
        return value


def look_and_say(value: str) -> str:
    parts: list[str] = []
    index = 0
    length = len(value)

    while index < length:
        digit = value[index]
        next_index = index + 1

        while next_index < length and value[next_index] == digit:
            next_index += 1

        parts.append(str(next_index - index))
        parts.append(digit)
        index = next_index

    return "".join(parts)


@app.post("/api/look-and-say")
def create_sequence(payload: SequenceRequest) -> dict[str, str]:
    try:
        return {"input": payload.value, "output": look_and_say(payload.value)}
    except Exception as exc:
        raise HTTPException(status_code=500, detail="Sequence transformation failed") from exc
