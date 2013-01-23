package com.example.calculadora;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0,
			btnSumar, btnRestar, btnMultiplicar, btnDividir, btnBorrar,
			btnNegativo, btnDecimal, btnIgual;
	private EditText txtResultado;
	private double num1, num2, res;
	private String operacion = "";
	private boolean esDecimal, esNegativo, limpiar_txtResultado = true,
			usadoNum1, usadoNum2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();
	}

	private void initComponents() {
		btn9 = (Button) findViewById(R.id.btn9);
		btn9.setOnClickListener(this);

		btn8 = (Button) findViewById(R.id.btn8);
		btn8.setOnClickListener(this);

		btn7 = (Button) findViewById(R.id.btn7);
		btn7.setOnClickListener(this);

		btn6 = (Button) findViewById(R.id.btn6);
		btn6.setOnClickListener(this);

		btn5 = (Button) findViewById(R.id.btn5);
		btn5.setOnClickListener(this);

		btn4 = (Button) findViewById(R.id.btn4);
		btn4.setOnClickListener(this);

		btn3 = (Button) findViewById(R.id.btn3);
		btn3.setOnClickListener(this);

		btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(this);

		btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(this);

		btn0 = (Button) findViewById(R.id.btn0);
		btn0.setOnClickListener(this);

		btnSumar = (Button) findViewById(R.id.btnSumar);
		btnSumar.setOnClickListener(this);

		btnRestar = (Button) findViewById(R.id.btnRestar);
		btnRestar.setOnClickListener(this);

		btnMultiplicar = (Button) findViewById(R.id.btnMultiplicar);
		btnMultiplicar.setOnClickListener(this);

		btnDividir = (Button) findViewById(R.id.btnDividir);
		btnDividir.setOnClickListener(this);

		btnNegativo = (Button) findViewById(R.id.btnNegativo);
		btnNegativo.setOnClickListener(this);

		btnDecimal = (Button) findViewById(R.id.btnDecimal);
		btnDecimal.setOnClickListener(this);

		btnIgual = (Button) findViewById(R.id.btnIgual);
		btnIgual.setOnClickListener(this);

		btnBorrar = (Button) findViewById(R.id.btnBorrar);
		btnBorrar.setOnClickListener(this);

		txtResultado = (EditText) findViewById(R.id.txtResultado);
	}

	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.btn0:
		case R.id.btn1:
		case R.id.btn2:
		case R.id.btn3:
		case R.id.btn4:
		case R.id.btn5:
		case R.id.btn6:
		case R.id.btn7:
		case R.id.btn8:
		case R.id.btn9:
			insertar_numero_del_boton((Button) v);
			break;

		case R.id.btnSumar:
		case R.id.btnRestar:
		case R.id.btnMultiplicar:
		case R.id.btnDividir:
			operacion_al_presionar_boton((Button) v);
			break;

		case R.id.btnBorrar:
			btnBorrar_onClick();
			break;

		case R.id.btnNegativo:
			btnNegativo_onClick();
			break;

		case R.id.btnDecimal:
			btnDecimal_onClick();
			break;

		case R.id.btnIgual:
			btnIgual_onClick((Button) v);
			break;
		}
	}

	private void insertar_numero_del_boton(Button btn) {
		if (!limpiar_txtResultado)
			txtResultado.setText(txtResultado.getText().toString()
					+ btn.getText());
		else {
			txtResultado.setText(btn.getText());
			limpiar_txtResultado = false;
		}
	}

	private void operacion_al_presionar_boton(Button btn) {
		if (!usadoNum1) {
			num1 = Double.parseDouble(txtResultado.getText().toString());
			usadoNum1 = true;
		} else if (!usadoNum2 && usadoNum1) {
			num2 = Double.parseDouble(txtResultado.getText().toString());
		}

		operar();
		operacion = btn.getText().toString();
	}

	private void operar() {
		if (operacion.length() > 0) {
			if (operacion.equals("+")) {
				res = num1 + num2;
			} else if (operacion.equals("-")) {
				res = num1 - num2;
			} else if (operacion.equals("*")) {
				res = num1 * num2;
			} else if (operacion.equals("/")) {
				if (num2 != 0)
					res = num1 / num2;
				else {
					btnBorrar_onClick();
					txtResultado.setText("Error - división entre 0.");
					return;
				}
			}

			num1 = res;

			if ((int) res == res)
				txtResultado.setText((int) res + "");
			else
				txtResultado.setText(res + "");

		}

		limpiar_txtResultado = true;
		esDecimal = false;
		esNegativo = false;
	}

	private void btnBorrar_onClick() {
		txtResultado.setText("");
		limpiar_txtResultado = true;
		num1 = 0;
		num2 = 0;
		res = 0;
		usadoNum1 = false;
		usadoNum2 = false;
		esDecimal = false;
		esNegativo = false;
		operacion = "";
	}

	private void btnNegativo_onClick() {
		if (!esNegativo) {
			txtResultado.setText("-" + txtResultado.getText().toString());
			esNegativo = true;
			limpiar_txtResultado = false;
		} else {
			txtResultado.setText(txtResultado.getText().toString()
					.replace("-", ""));
			esNegativo = false;
		}
	}

	private void btnDecimal_onClick() {
		if (!esDecimal) {
			if (!limpiar_txtResultado) {
				txtResultado.setText(txtResultado.getText().toString() + ".");
			} else {
				txtResultado.setText("0.");
				limpiar_txtResultado = false;
			}
			
			esDecimal = true;
		}
	}

	private void btnIgual_onClick(Button btn) {
		operacion_al_presionar_boton(btn);

		usadoNum1 = false;
		usadoNum2 = false;
		operacion = "";
	}

}
