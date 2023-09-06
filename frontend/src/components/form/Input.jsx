import {  useField,  } from "formik";

import PropTypes from "prop-types";

function Input({accept,onChange, label,type, defaultValue,...props }) {
  const [field, meta, helpers] = useField(props);
  return (
    <label>
      <div>{label}</div>
      <input accept={accept} onChange={onChange && ((e) => onChange(e.target.value))} type={type} {...field} {...props} defaultValue={defaultValue}/>
    </label>
  );
}

Input.propTypes = {
  label: PropTypes.string.isRequired,
};

export default Input;
