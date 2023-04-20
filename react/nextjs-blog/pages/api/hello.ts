import { NextApiRequest, NextApiResponse } from "next";

// this page accessible from: http://localhost:3000/api/hello

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  res.status(200).json({ text: "Hello World" });
}
