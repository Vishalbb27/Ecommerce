import { ComponentPropsWithoutRef, forwardRef } from "react";
import "../../css/input.css"

type InputProps = {
  label: string;
  id: string;
} & ComponentPropsWithoutRef<"input">;

const Input = forwardRef<HTMLInputElement, InputProps>(function Input(
  { label, id, ...props },
  ref
) {
  return (
    // <div className="form-goup col-xs-4">
    <input
      id={id}
      name={id}
      {...props}
      ref={ref}
      defaultValue={props.defaultValue}
      onChange={(e) => e.currentTarget.value}
    />
    // </div>
  );
});

export default Input;
