using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace SupaCalc
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private string mode;
        public MainWindow()
        {
            InitializeComponent();
            mode = dropdownMode.Text;
            indexForm.Focus();
            inputValue.Text = "";
            

        }

        private void ComboBoxMode_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            mode= dropdownMode.Text;
        }

        private void indexForm_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.Key == Key.Delete || e.Key == Key.Back)
            {

                if (inputValue.Text.Length > 0)
                {
                    inputValue.Text = inputValue.Text.Substring(0, inputValue.Text.Length - 1);
                }

            }
        }

        private void Button_Exit(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void Button_Reset(object sender, RoutedEventArgs e)
        {
            inputValue.Text = null;
            outputValue.Text = null;
            dropdownMode.SelectedValue=dropdownMode.Items.GetItemAt(0);
        }

        private void Window_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            Regex rx = new Regex(@"[0123456789+\-*/^!()]+");

            Regex operators = new Regex(@"[+\-*/^]+");



            if (rx.IsMatch(e.Text))
            {

                if(inputValue.Text.Length < 1 && operators.IsMatch(e.Text))
                {

                    MessageBox.Show("Bitte erst eine Zahl dann einen Operator eingeben");

                }
                else if (operators.IsMatch(e.Text) && operators.IsMatch(inputValue.Text.Substring(inputValue.Text.Length-1)))
                {

                    MessageBox.Show("Keine 2 Operatoren nacheinander möglich");

                }
                else
                {
                    inputValue.Text += e.Text;
                }

                

            }

        }

        private void Button_Calculate(object sender, RoutedEventArgs e)
        {
            outputValue.Text = inputValue.Text;
        }
    }
}
