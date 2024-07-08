import { ComponentPropsWithoutRef, forwardRef } from "react";
import { CategoryDetails } from "../../slice/categoryApiSlice";
// import { UserState } from "../../slice/userapiSlice";

//#TODO
export type UserState = {
  name: String | null;
  email: String | null;
  username: String | null;
  id: number | 0;
};

type SelectProps = {
  label: string;
  id: string;
  options: CategoryDetails[];
  defaultValue?: string;
  optionName: string;
  onChange?: (event: React.ChangeEvent<HTMLSelectElement>) => void;
} & ComponentPropsWithoutRef<"select">;

const Select = forwardRef<HTMLSelectElement, SelectProps>(function Select(
  { label, id, options, defaultValue, onChange, optionName, ...props },
  ref
) {
  return (
    <div className="form-group col-xs-4 d-flex flex-column border-0">
      <select
        id={id}
        name={id}
        ref={ref}
        defaultValue={defaultValue}
        onChange={onChange}
        {...props}
      >
        <option key={id} value={""} className="bg-white text-dark">
          {optionName}
        </option>
        {options.map((option) => (
          <option
            key={option.id}
            value={option.id}
            className="bg-white text-dark"
          >
            {option.name}
          </option>
        ))}
      </select>
    </div>
  );
});

export default Select;
