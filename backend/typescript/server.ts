import Fastify from "fastify";
import { z } from "zod";

const app = Fastify({ logger: true });

const SequenceRequest = z.object({
  value: z
    .string()
    .regex(/^\d+$/, "value must contain decimal digits only")
    .min(1)
    .max(10000),
});

function lookAndSay(value: string): string {
  let output = "";

  for (let index = 0; index < value.length; index += 1) {
    let count = 1;

    while (index + count < value.length && value[index + count] === value[index]) {
      count += 1;
    }

    output += String(count) + value[index];
    index += count - 1;
  }

  return output;
}

app.post("/api/look-and-say", async (request, reply) => {
  const parsed = SequenceRequest.safeParse(request.body);

  if (!parsed.success) {
    return reply.code(400).send({ error: parsed.error.flatten() });
  }

  return {
    input: parsed.data.value,
    output: lookAndSay(parsed.data.value),
  };
});

const start = async () => {
  try {
    await app.listen({ port: 8080, host: "0.0.0.0" });
  } catch (error) {
    app.log.error(error);
    process.exit(1);
  }
};

start();
