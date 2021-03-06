doc "Given a nonempty sequence of `Numeric` values, return 
     the product of the values."
see (sum)
shared Value product<Value>({Value+} values) 
        given Value satisfies Numeric<Value> {
    variable value product = values.first;
    for (val in values.rest) {
        product*=val;
    }
    return product;
}
