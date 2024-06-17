import { render, screen } from "@testing-library/react";
import Info from "@/components/Info";

describe("infoTest", () => {
  it("render", () => {
    render(<Info />);
    expect(screen.queryByText("Digital Card")).toBeInTheDocument();
  });
});
