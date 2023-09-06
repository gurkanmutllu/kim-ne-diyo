import {  useField,  } from "formik";

import PropTypes from "prop-types";
export default function File({accept,onChange, label,type, defaultValue,...props }) {
    const [field, meta, helpers] = useField(props);
    const changeHandle= (e) => {
        helpers.setValue(e.target.files[0])
        // const file = e.currentTarget.files[0];
        // if (file) {
        //     const reader = new FileReader();
        //     reader.onload = (e) => {
        //         const byteArray = new Uint8Array(e.target.result);
        //         helpers.setValue(byteArray); // Set the value in Formik's form state
        //     };
        //     reader.readAsArrayBuffer(file);
        // }
    };
    return (
        <label>
            <div>{label}</div>
            <input  accept={accept} onChange={changeHandle} type="file"  {...props} defaultValue={defaultValue}/>
        </label>
    );
}




