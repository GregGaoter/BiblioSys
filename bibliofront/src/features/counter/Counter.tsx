import React, { useState } from "react";
import { useAppDispatch, useAppSelector } from "../../app/store/hooks";
import styles from "./Counter.module.css";
import { decrement, increment, incrementByAmount } from "./counterSlice";
// import { decrement, increment, incrementByAmount, selectCount } from "./counterSlice";

export function Counter() {
  // const count = useAppSelector(selectCount);
  const dispatch = useAppDispatch();
  const [incrementAmount, setIncrementAmount] = useState("2");

  return (
    <div>
      <div className={styles.row}>
        <button className={styles.button} aria-label="Increment value" onClick={() => dispatch(increment())}>
          +
        </button>
        <span className={styles.value}>{0}</span>
        {/* <span className={styles.value}>{count}</span> */}
        <button className={styles.button} aria-label="Decrement value" onClick={() => dispatch(decrement())}>
          -
        </button>
      </div>
      <div className={styles.row}>
        <input
          className={styles.textbox}
          aria-label="Set increment amount"
          value={incrementAmount}
          onChange={(e) => setIncrementAmount(e.target.value)}
        />
        <button className={styles.button} onClick={() => dispatch(incrementByAmount(Number(incrementAmount) || 0))}>
          Add Amount
        </button>
      </div>
    </div>
  );
}
